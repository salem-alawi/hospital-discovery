<template>
  <div>
    {{count}}
  </div>
</template>


<script>


import DoctorService from "@/service/DoctorService";


export default {


  props: ['hospitalSectionId'],
  data: ()=>{
    return{
      count: 0
    }

  },
  mounted() {

    DoctorService.findDoctorCountBySectionIdPublic(this.hospitalSectionId,this.hospitalSectionId).then(resp=>{

      if(resp.status===200)
        this.count=resp.data
    });
  },
  methods:{

  }


};

</script>