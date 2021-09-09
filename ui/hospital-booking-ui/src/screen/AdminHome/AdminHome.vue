<template>
    <div class="AdminHome">



      <v-app>
        <v-app-bar style="background-color: #1936ee" app>
          <!-- -->
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

          <h1>مدير النطام العام</h1>
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
