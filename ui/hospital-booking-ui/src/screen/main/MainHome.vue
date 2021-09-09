<template>

  <div>

    <v-app>
      <v-app-bar style="background-color: red" app>
        <!-- -->
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

        <h1>تطبيق دليل المستشفيات</h1>
      </v-app-bar>
      <v-content>

        <a href="/admin-login">تسجيل دخول الاداره</a>


        <h1>النطام العام</h1>



      </v-content>
    </v-app>

  </div>


</template>


<script>


export default {

  props:[],
  data: ()=>{
    return{
      drawer: false
    }
  },
  mounted() {


  }

}

</script>