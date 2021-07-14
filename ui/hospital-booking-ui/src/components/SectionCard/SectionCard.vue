<template>
    <div class="SectionCard">

        <v-card
                @click="$emit('click')"
                class="mx-auto my-12"
                max-width="274"
        >
            <v-img
                    height="150"
                    :src="section.coverImage"
            ></v-img>
            <v-card-title>{{section.name}}</v-card-title>
        </v-card>
    </div>
</template>

<script>
    export default {
        props: ['section']
    };
</script>
