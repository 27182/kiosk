import { createWebHistory, createRouter } from "vue-router";
import HelloWorld from "@/views/HelloWorld.vue"
import AdminPage from "@/views/AdminPage.vue"
import MenuPage from "@/views/MenuPage.vue"
import AdminMenu from "@/components/AdminMenu"
import AdminOrder from "@/components/AdminOrder"
import CRUDACTION from "@/components/CRUDACTION.vue"



const routes = [
    {
        path: "/",
        name: "HelloWorld",
        component: HelloWorld,
    },
    {
        path: "/menu",  // TestComponent.vue로 이동할 Path
        name: "MenuPage",  // router name
        component: MenuPage,  // Path로 이동될 Component
    },
    {
        path: "/admin",
        name: "AdminPage",
        component: AdminPage,
        children: [
            {
                path: "menu",
                name: "AdminMenuPage",
                component: AdminMenu, 
            },
            {
                path: "order",
                name: "AdminOrderPage",
                component: AdminOrder,
            },


        ]
    },

    {
        path: "/crud",  // TestComponent.vue로 이동할 Path
        name: "ddd",  // router name
        component: CRUDACTION,  // Path로 이동될 Component
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;