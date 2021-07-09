<template>
    <div class="HospitalCard">


        <v-card
                class="mx-auto my-12"
                max-width="374"
        >
<!--            <template slot="progress">-->
<!--                <v-progress-linear-->
<!--                        color="deep-purple"-->
<!--                        height="10"-->
<!--                        indeterminate-->
<!--                ></v-progress-linear>-->
<!--            </template>-->

            <v-img
                    height="250"
                    :src="hospital.hospitalStaticConfig.coverImage"
            ></v-img>


            <v-icon @click="$emit('openMap',hospital)" large style="float: left;margin: 17px" class="mr-1">
                mdi-map
            </v-icon>
            <v-card-title>{{hospital.name}}</v-card-title>



<!--            <v-card-text>-->

<!--            </v-card-text>-->

            <v-divider class="mx-4"></v-divider>

            <v-card-title>{{hospital.description}}</v-card-title>

            <v-card-actions>
                <v-btn
                        color="deep-purple lighten-2"
                        text
                        @click="openOneHospital(hospital)"
                >
                    تفاصيل
                </v-btn>
            </v-card-actions>
        </v-card>

    </div>
</template>

<script>

    // import axios from 'axios'
    // import {mapActions, mapGetters} from 'vuex';

    export default {
        props: ['hospital'],
        data:()=>({}),
        computed: {
            // ...mapGetters(['allHospitals'])
        },
        methods:{
            openOneHospital(oneHospital){
                console.log(oneHospital);
                this.$router.push('/admin/hospitals/'+oneHospital.id);
            }
        }
    };
</script>
