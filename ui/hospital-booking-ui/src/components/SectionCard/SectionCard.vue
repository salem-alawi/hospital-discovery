<template>
    <div class="SectionCard">

        <v-card
                @click="$emit('click')"
                class="mx-auto my-12"
                max-width="274"
        >
          <v-card-title>{{section.name}}</v-card-title>
          <v-img
                    height="150"
                    :src="section.coverImage"
            ></v-img>
            <v-card-title> <ShowDoctorCountBySectionId :hospital-section-id="hospitalSectionId" /> عدد الاطباء</v-card-title>

        </v-card>
    </div>
</template>

<script>

    import ShowDoctorCountBySectionId from "@/components/ٍShowDoctorCountBySectionId/ShowDoctorCountBySectionId";
    export default {
      components: {ShowDoctorCountBySectionId},
      props: ['section','hospitalSectionId']
    };
</script>
