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

    console.log('==============dddd=='+ this.hospitalSectionId)

    DoctorService.findDoctorCountBySectionId(this.hospitalSectionId,this.hospitalSectionId).then(resp=>{
      console.log('ddddddddddddd='+resp.data)


      if(resp.status===200)
        this.count=resp.data
    });
  },
  methods:{

  }


};

</script>