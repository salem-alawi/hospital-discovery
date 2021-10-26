<template>

  <div class="container--fluid">
    <a href="/admin-login">تسجيل دخول الاداره</a>
    <div class="d-flex flex-wrap justify-center">
    <PublicHospitalCard v-for="hospital in allPublicHospital" :key="hospital.id" :hospital="hospital" @openMap="(item)=> openLocation(item)" />
    </div>

    <v-dialog v-model="locationDialog" max-width="1100">
      <v-card>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  class="d-flex child-flex"
                  cols="12">
                <v-card>
                  <iframe
                      v-bind:src="locationUrl"
                      width="100%" height="450" frameborder="0"
                      style="border: 0;"
                      class="grey lighten-2">
                  </iframe>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <!--                        <v-btn color="blue darken-1" text @click="closeMap">اغلاق</v-btn>-->
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>

import HospitalService from "../../service/HospitalService";
import PublicHospitalCard from "../../components/PublicHospitalCard";


export default {
  components: {PublicHospitalCard},
  data: () => {

    return {
      allPublicHospital: [],
      locationDialog: false,
      locationUrl: '',
    }
  },
  methods: {
    openLocation(item) {
      console.log(JSON.stringify(item, null, 2));
      //window.open("http://maps.google.com/maps?q=loc:" + item.latitude + "," + item.longitude, "_blank");
      this.locationUrl = "https://maps.google.com/maps?q=" + item.latitude + ", " + item.longitude + "&z=15&output=embed";
      this.locationDialog = true;
    },
    initial() {

      HospitalService.findAllPublicHospital().then((resp) => {
        if (resp.status === 200) {
          this.allPublicHospital = resp.data.content;
        }
      });

    }


  },
  mounted() {

    this.initial();
  }


}
</script>
