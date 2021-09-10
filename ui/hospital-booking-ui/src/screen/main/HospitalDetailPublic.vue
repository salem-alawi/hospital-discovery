<template>

  <div>

<!--    <h1> wellcom {{id}}</h1>-->


    <carousel-3d :autoplay="true" :inverse-scaling="150" :space="200"  :autoplay-timeout="30000" :display="8" :width="900" :height="500"  >
      <slide   :index="0">
        Slide 1 Content
      </slide>
      <slide :index="1">
        Slide 2 Content
      </slide>
      <slide :index="2">
        Slide 2 Content
      </slide>
      <slide :index="3">
        Slide 2 Content
      </slide>
      <slide :index="4">
        Slide 2 Content
      </slide>
      <slide :index="5">
        Slide 2 Content
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
      sections:[]

    }
  },

  methods: {
    initial() {

      console.log('init')
      SectionService.findAllByHospitalPublic(this.id).then(resp=>{

        console.log(resp.data)
        if(resp.status===200)
          this.sections=resp.data;

      })



    }


  },
  mounted() {
    this.initial();
  }

}

</script>