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

<div class="d-flex justify-center">
  <h3>الاقسام</h3>
</div>

    <div class="d-flex justify-center">
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