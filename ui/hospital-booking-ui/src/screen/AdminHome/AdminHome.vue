<template>
    <div class="AdminHome">
        <v-app>
            <v-app-bar style="background-color: red" app>
                <!-- -->
                <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

                <h1>ِمدير الموقع</h1>
            </v-app-bar>

<!--            <v-navigation-drawer v-model="drawer"-->
<!--                                 absolute-->
<!--                                 dir="rtl"-->
<!--                                 right-->
<!--                                 temporary>-->

<!--                <v-list-->
<!--                        nav-->
<!--                        dense-->
<!--                >-->
<!--                    <router-link to="/admin/hospitals" active-class="router-link-exact-active"-->
<!--                                 style="text-decoration: none">-->

<!--                        <v-list-item :class="'/admin/hospitals' === $route.path ? 'highlighted' : ''">-->
<!--                            <v-list-item-action>-->
<!--                                <v-icon>mdi-home</v-icon>-->
<!--                            </v-list-item-action>-->
<!--                            <v-list-item-content>-->
<!--                                <v-list-item-title>الرئيسية</v-list-item-title>-->
<!--                            </v-list-item-content>-->

<!--                        </v-list-item>-->
<!--                    </router-link>-->
<!--                    <router-link to="/admin/sections" active-class="router-link-exact-active"-->
<!--                                 style="text-decoration: none">-->
<!--                        <v-list-item :class="'/admin/sections' === $route.path ? 'highlighted' : ''">-->

<!--                            <v-list-item-content>-->
<!--                                <v-list-item-title>الاقسام</v-list-item-title>-->
<!--                            </v-list-item-content>-->

<!--                        </v-list-item>-->
<!--                    </router-link>-->

<!--                </v-list>-->
<!--            </v-navigation-drawer>-->
            <v-content>
                <router-view></router-view>
            </v-content>
        </v-app>


    </div>
</template>
<style>
    .highlighted {
        border-right-width: thick !important;
        background-color: cornflowerblue !important;
        border-radius: 12px !important;
        margin: 10px !important;
    }
</style>
<script>
    export default {
        props: {},
        data: () => {
            return {
                drawer: false
            }
        },
        
    };
</script>
