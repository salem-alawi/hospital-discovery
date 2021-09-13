<template>

  <v-app>
      <v-container fluid>
          <div class="d-flex justify-center">
            <v-card class="elevation-12" style="width: 400px">
              <v-toolbar dark color="#ED534F">
                <v-toolbar-title>تسجيل دخول</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field

                      label="اسم المستخدم"
                      type="text"
                      value="admin"
                  ></v-text-field>
                  <v-text-field
                      label="كلمة المرور"
                      type="password"
                      value="admin"
                  ></v-text-field>
                </v-form>
              </v-card-text>
                <div class="d-flex justify-center">
                  <v-btn @click="goToAdmin()" color="#ED534F" ><span style="color: #fff">تسجيل الدخول</span></v-btn>
                </div>

              <br />
            </v-card>
          </div>
      </v-container>


  </v-app>

</template>


<script>

export default {

props:[],
  data:()=>{

  },
  methods:{
    goToAdmin(){

      window.location.href ="/admin/hospitals";
    }

  }


}

</script>