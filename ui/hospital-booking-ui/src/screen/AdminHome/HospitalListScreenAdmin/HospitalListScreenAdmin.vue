<template>


    <div class="HospitalListScreenAdmin">
        <div class="container--fluid">

            <div class="d-flex flex-wrap justify-center">
                <div   style="margin: 10px"  v-for="hos in allHospitals" :key="hos.id" >
                    <HospitalCard :hospital=hos @openMap="(item)=> openLocation(item)"/>
                </div>
            </div>

        </div>
        <CreateNewHospital @closeDia="createClose" :show="createHospitalDialog"/>
        <v-dialog v-model="locationDialog" max-width="1100">
            <v-card>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col
                                    class="d-flex child-flex"
                                    cols="12">
                                <v-card>
                                    <iframe
                                            v-bind:src="locationUrl"
                                            width="100%" height="450" frameborder="0"
                                            style="border: 0;"
                                            class="grey lighten-2">
                                    </iframe>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <!--                        <v-btn color="blue darken-1" text @click="closeMap">اغلاق</v-btn>-->
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-btn
                @click="createHospitalDialog=true"
                color="red"
                fab
                large
                class="v-btn--example"
        >
            <v-icon>mdi-plus</v-icon>
        </v-btn>
    </div>
</template>
<style>
    /* This is for documentation purposes and will not be needed in your application */
    .v-btn--example {
        bottom: 20px;
        left: 20px;
        position: fixed;
    }
</style>
<script>
    import HospitalCard from "../../../components/HospitalCard/HospitalCard";
    import {mapActions, mapGetters} from 'vuex';
    import axios from 'axios'
    import CreateNewHospital from '../../../dialogs/CreateNewHospital/CreateNewHospital';



    export default {

        data: () => ({
                createHospitalDialog: false,
                fab: false,
                hidden: false,
                tabs: null,
                locationDialog: false,
                locationUrl: '',
                dd: {
                    hospitalStaticConfig: {
                        coverImage: 'https://lh3.googleusercontent.com/proxy/AHu7dUX_eN0TWhyzjnbhPPwWp4Is4e3jSSm6EuQ7QFmnKrUbSTVf438uT_YY57LrpIpzyDedxsFpGclhU2CUXSbHtvpdVdgrKj7A-hDd8lAA30B1rPZeFr9XxISuFt8IcI_Z01Mnv9G9tmg'
                    }
                }
            }


        ),
        methods: {
            ...mapActions(['loadAllHospitals']),
            createClose(val) {
                if (val == false)
                    this.createHospitalDialog = false;

            },
            openLocation(item) {
                console.log(JSON.stringify(item, null, 2));
                //window.open("http://maps.google.com/maps?q=loc:" + item.latitude + "," + item.longitude, "_blank");
                this.locationUrl = "https://maps.google.com/maps?q=" + item.latitude + ", " + item.longitude + "&z=15&output=embed";
                this.locationDialog = true;
            },
            initialize() {
                axios.get('http://52.36.50.145:8444/admin/hospitals?size=100').then(resp => {
                    this.loadAllHospitals(resp.data.content);
                });
            }

        },
        mounted() {
            this.initialize();

        },
        components: {
            CreateNewHospital,
            HospitalCard
        }
        ,
        computed: {
            ...mapGetters(['allHospitals']),

        },
        props: {}
        ,
    }
    ;
</script>
