<template>

  <div>

<!--    <h1> wellcom {{id}}</h1>-->


    <carousel-3d v-if="hospital" :autoplay="true" :inverse-scaling="150" :space="200"  :autoplay-timeout="30000" :display="8" :width="900" :height="500"  >
      <slide   :index="0">
        <v-img  :src="hospital.hospitalStaticConfig.coverImage"   />
      </slide>
      <slide v-for="(image,index) in hospital.hospitalStaticConfig.images" :key="image" :index="index+1">
        <v-img  :src="image" />
      </slide>
    </carousel-3d>

    <br />
    <v-divider />
    <br />
    <v-row v-if="hospital">
      <v-col style="background-color: rgba(211,211,211,0.27);border-radius:18px;margin: 20px;height: 400px">
        <div  class="container">
        <h3 >نبده عن المشفى</h3>
        <h4 >{{hospital.description}}</h4>
        </div>
      </v-col >
      <v-col style="height: 400px">
        <div  class="container">
        <iframe
            :src="'https://maps.google.com/maps?q=' + hospital.latitude + ', ' + hospital.longitude + '&z=15&output=embed'"
            width="100%" height="400"  frameborder="0"
            style="border: 0;border-radius: 18px"
            class="grey lighten-2">
        </iframe>
        </div>
      </v-col>

    </v-row>
<br />
    <v-divider />
    <br />
<div class="d-flex justify-center">
  <h3>الاقسام</h3>
</div>

    <div class="d-flex flex-wrap justify-center" style="background-color: rgba(211,211,211,0.27);border-radius:18px;margin: 20px;">
      <HospitalSectionPublic style="width: 300px" v-for="section in sections" :key="section.id" :hospital-section="section" />
    </div>


    <HospitalFooterPublic />

  </div>
</template>

<script>

import { Carousel3d, Slide } from 'vue-carousel-3d';
import SectionService from "@/service/SectionService";
import HospitalSectionPublic from "@/components/HospitalSectionPublic";
import HospitalFooterPublic from "@/screen/main/HospitalFooterPublic";
import HospitalService from "@/service/HospitalService";




export default {
  components: {
    HospitalFooterPublic,
    HospitalSectionPublic,
    Carousel3d,
    Slide
  },
  props: ['id'],
  data: () => {

    return {
      sections:[],
      hospital: undefined

    }
  },

  methods: {
    initial() {

      console.log('init')
      SectionService.findAllByHospitalPublic(this.id).then(resp=>{
        if(resp.status===200)
          this.sections=resp.data;
      })

      HospitalService.findOneHospitalPublic(this.id).then((resp)=>{
        console.log(JSON.stringify(resp.data,null,2))
        if(resp.status===200){
          this.hospital=resp.data;
        }
      })




    }


  },
  mounted() {
    this.initial();
  }

}

</script>