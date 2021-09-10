<template>

  <div>

    <v-app>
      <v-app-bar style="background-color: #EE534F" app>
        <!-- -->
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

        <h1>تطبيق دليل المستشفيات</h1>
      </v-app-bar>
      <v-content>


        <router-view></router-view>

      </v-content>
    </v-app>

  </div>


</template>


<script>


export default {

  props: [],
  data: () => {
    return {
      drawer: false
    }
  },
  mounted() {


  }

}

</script>