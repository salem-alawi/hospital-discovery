<template>


  <v-card
      class="mx-auto my-12"
      max-width="274"
  >
    <v-card-title > <div style="margin: auto">{{ hospitalSection.section.name }}</div></v-card-title>
    <v-img
        height="150"
        :src="hospitalSection.section.coverImage"
    ></v-img>
    <v-card-title>
      عدد الاطباء  :
      <ShowDoctorCountInHospitalSectionPublic :hospital-section-id="hospitalSection.id"/>
    </v-card-title>
  </v-card>

</template>

<script>

import ShowDoctorCountInHospitalSectionPublic from "@/components/ShowDoctorCountInHospitalSectionPublic";
export default {
  components: {ShowDoctorCountInHospitalSectionPublic},
  props: ['hospitalSection'],
  methods:{


  },
  mounted() {
  }

}
</script>