<template>
  <div>
    {{ hospitalSectionId }}
    <v-card
        class="mx-auto my-12"
        max-width="274"
    >
      <v-card-title>
        <div  style="margin: auto">
         {{ doctor.name }}
        </div>
      </v-card-title>
      <v-img
          height="150"
          :src="doctor.image"
      ></v-img>
      <v-card-title>
        {{ doctor.about }}
      </v-card-title>
      <v-card-actions>
        <v-icon style="cursor: pointer" @click="removeDoctors()">
          {{ icons.mdiDelete }}
        </v-icon>
        <v-icon style="cursor: pointer" @click="removeDoctors()">
          {{ icons.mdiHumanEdit }}
        </v-icon>
      </v-card-actions>
    </v-card>
  </div>
</template>


<script>


import {mdiDelete, mdiHumanEdit} from "@mdi/js";
// import HospitalSectionService from "@/service/HospitalSectionService";

export default {
  props: ['doctor'],
  data: () => {
    return {
      icons: {
        mdiDelete,
        mdiHumanEdit
      },
    }

  },
  methods: {
    removeDoctors() {


    }
  },
  mounted() {


    console.log(' docktor section id=' + this.hospitalSectionId);
    console.log(this.doctor);
  }
};
</script>