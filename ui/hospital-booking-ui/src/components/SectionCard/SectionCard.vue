<template>
  <div class="SectionCard">

    <v-card
        class="mx-auto my-12"
        max-width="274"
    >
      <v-card-title>{{ section.name }}</v-card-title>
      <v-img
          height="150"
          :src="section.coverImage"
      ></v-img>
      <v-card-title>
        <ShowDoctorCountBySectionId :hospital-section-id="hospitalSectionId"/>
        عدد الاطباء
      </v-card-title>
      <v-card-actions>
        <v-icon  style="cursor: pointer" @click="removeHospitalSection()">
          {{ icons.mdiDelete }}
        </v-icon>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>

import {
  mdiDelete,
} from '@mdi/js'


import ShowDoctorCountBySectionId from "@/components/ٍShowDoctorCountBySectionId/ShowDoctorCountBySectionId";
import HospitalSectionService from "@/service/HospitalSectionService";


export default {
  components: {ShowDoctorCountBySectionId},
  props: ['section', 'hospitalSectionId'],
  data: () => {
    return {
      icons: {
        mdiDelete,
      },
    }

  },
  methods: {
    removeHospitalSection() {

      HospitalSectionService.removeHospitalSectionById(this.hospitalSectionId).then((resp) => {

        if (resp.status === 200) {
          location.reload();
        }

      })

    }
  }
};
</script>
