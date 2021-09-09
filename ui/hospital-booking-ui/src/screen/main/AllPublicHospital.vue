<template>

</template>

<script>

import HospitalService from "../../service/HospitalService";




export default {
  data: ()=>{

    return{
      allPublicHospital:[]

    }
  },
  methods:{
    initial(){

      HospitalService.findAllPublicHospital((resp)=>{
        if(resp.status===200){
          this.allPublicHospital=resp.data;
        }
      })

    }


  },
  mounted() {

  }


}
</script>