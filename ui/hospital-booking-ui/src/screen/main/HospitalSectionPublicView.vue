<template>
<div>

  <div class="d-flex flex-wrap justify-center">
    <div style="width: 300px;" v-for="doctor in doctors" :key="doctor.id">
      <DoctorCardAdmin :doctor="doctor" :edit="false" :hospital-id="id"/>
    </div>
  </div>




</div>
</template>

<script>



import DoctorCardAdmin from "@/components/DoctorCard/DoctorCardAdmin";
import DoctorService from "@/service/DoctorService";

export default {
  props: ['id','sectionId'],
  components: {DoctorCardAdmin},
  data: ()=>{
    return {
      doctors: []
    };
  },
  mounted() {

    console.log(this.id);
  DoctorService.findAllDoctorsByHospitalId(this.id).then(resp=>{

    if(resp.status==200){
      this.doctors=resp.data;
    }



  })
  }

}
</script>
