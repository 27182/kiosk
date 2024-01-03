package com.kio.my.service;


import com.kio.my.controller.OrderController;
import com.kio.my.domain.Ordered;
import com.kio.my.domain.Payment;
import com.kio.my.dto.OrderDTO;
import com.kio.my.dto.ReadyResponseDTO;
import com.kio.my.repository.OrderRepository;
import com.kio.my.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;


@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class KakaoPayServiceImpl implements KakaoPayService{

    @Autowired
    OrderController orderController;

    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드

    @Value("${KAKAO_ADMIN_KEY}")
    private String admin_Key; //

    @Value("${PRESENT_SERVICE_URL}")
    private String present_url;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final PaymentRepository paymentRepository;

    public ReadyResponseDTO readytoPay(OrderDTO orderDTO){

         Map map = orderController.updateOrder(orderDTO);



        String key = "KakaoAK " + admin_Key;
//        WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com/v1/payment/ready")
//                .build();



        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", map.get("ono").toString());
        parameters.add("partner_user_id", "Guest");
        parameters.add("item_name", orderDTO.getContent().replace("{","").replace("}",""));
        parameters.add("quantity", "1");
        parameters.add("total_amount", orderDTO.getTotalPrice().toString());
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url",  present_url+ "/approve/"+ map.get("ono").toString()); // 성공 시 redirect url
        parameters.add("cancel_url",  present_url + "/menu"); // 취소 시 redirect url
        parameters.add("fail_url", present_url + "/menu"); // 실패 시 redirect url


//        String a = webClient.post().uri("")
//                .headers(h->{h.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                    h.add("Authorization",key);
//                })
//                .bodyValue(parameters)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();


        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization",key);
        headers.add("Accept", "application/json");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(parameters, headers);

        ReadyResponseDTO readyResponseDTO = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/ready"
                ,body,ReadyResponseDTO.class);

        paymentRepository.save(new Payment(readyResponseDTO.getTid(),map.get("ono").toString()));


        return readyResponseDTO;



    }


    public Ordered approvePayment(String pg_token, String ono){
        String key = "KakaoAK " + admin_Key;
        Optional<Payment> pay = paymentRepository.findByOno(ono);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization",key);
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", ono);
        parameters.add("partner_user_id", "Guest");
        parameters.add("tid", pay.get().getTid());
        parameters.add("pg_token", pg_token);


        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(parameters, headers);

        String check = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/approve"
               ,body, String.class);

         Optional<Ordered> find = orderRepository.findById(Long.parseLong(ono));

         return find.get();
    }
}
