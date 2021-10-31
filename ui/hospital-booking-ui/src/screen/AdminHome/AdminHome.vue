<template>
  <div class="AdminHome">


    <v-app>
      <v-app-bar style="background-color: #EE534F" app>
        <!-- -->
        <!--          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>-->
        <router-link style="text-decoration: none
" to="/admin/hospitals">
          <h1 style="color: #000" >مدير النظام العام</h1>
        </router-link>
      </v-app-bar>
      <v-content>
        <router-view></router-view>
      </v-content>
    </v-app>

  </div>
</template>

<script>
export default {
  props: {},
};
</script>
