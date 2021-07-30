<template>
    <v-dialog v-model="show" max-width="1100">


        <v-card style="padding: 40px">
            <div class="d-flex justify-center">
                <h3>
                    اضافة مستشفى
                </h3>
            </div>
            <hr/>
            <div class="CreateNewHospital">
                <v-row style="padding: 40px">
                    <v-col
                            class="d-flex child-flex"
                            cols="12">
                        <v-card style="border-radius: 17px;">
                            <v-row no-gutters justify="center" align="center">
                                <v-col>
                                    <v-file-input
                                            v-model="file"
                                            style="width: 300px"
                                            show-size
                                            label="اختار الصور"
                                            accept="image/*"
                                            @change="selectImage"
                                    ></v-file-input>
                                </v-col>
                                <v-col cols="4" class="pl-2">
                                    <div v-if="progress">
                                        <div>
                                            <v-progress-linear
                                                    v-model="progress"
                                                    color="light-blue"
                                                    height="25"
                                                    reactive
                                            >
                                                <strong>{{ progress }} %</strong>
                                            </v-progress-linear>
                                        </div>
                                    </div>
                                    <div v-if="previewImage">
                                        <div>
                                            <img style="width: 150px;height: 100px" class="preview my-3"
                                                 :src="previewImage" alt=""/>
                                        </div>
                                    </div>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="lg-4 md-5 sm-11">
                                    <v-text-field
                                            style="width: 300px;margin: auto;padding-top: 30px"
                                            v-model="name"
                                            label="اسم المستشفى"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-textarea
                                            color="lg-8- md-7 sm-11"
                                            style="padding-right: 100px;padding-left: 100px"
                                            v-model="description"
                                            label="وصف المستشفى"
                                            required
                                    ></v-textarea>
                                </v-col>
                            </v-row>
                            <v-row style="padding-right: 100px;padding-left: 100px">

                                <v-col>
                                    <v-text-field
                                            v-model="longitude"
                                            label="خط الطول"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                            v-model="latitude"
                                            label="خط العرض"
                                            required
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="lg-4 md-3 sm-0"></v-col>
                                <v-col cols="lg-4 md-6 sm-12">
                                    <v-select
                                            :value="0"
                                            item-value="value"
                                            item-text="name"
                                            :items="type"
                                            label="Standard"
                                    ></v-select>

                                </v-col>
                                <v-col cols="lg-4 md-3 sm-0"></v-col>
                            </v-row>
                            <v-row>
                                <v-col class="d-flex justify-center" style="padding-bottom: 30px">
                                    <v-btn

                                            color="primary"
                                            @click="saveNewHospitalToApi()"
                                    >
                                        حفظ
                                    </v-btn>

                                </v-col>
                            </v-row>
                        </v-card>
                    </v-col>
                </v-row>
            </div>
        </v-card>
    </v-dialog>
</template>

<script>

    import UploadFilesService from "../../service/UploadFilesService";
    import HospitalService from "../../service/HospitalService";

    export default {
        mounted() {
            setInterval(() => {

            }, 2000)
        },
        data: () => {
            return {
                dia: false,
                file: null,
                currentImage: undefined,
                previewImage: undefined,
                progress: 0,
                name: '',
                description: '',
                longitude: '',
                latitude: '',
                type: [{
                    name: 'حكومي',
                    value: 0
                }, {
                    name: 'خاص',
                    value: 1
                }]
            }
        },
        methods: {
            upload() {
                if (!this.currentImage) {
                    this.message = "Please select an Image!";
                    return;
                }
                this.progress = 0;
                UploadFilesService.upload(this.currentImage, (event) => {
                    this.progress = Math.round((100 * event.loaded) / event.total);
                })
                    .then((response) => {
                        this.coverImage = response.data.name;
                    });
            },
            saveNewHospitalToApi() {


                HospitalService.createNewSection({
                    name: this.name,
                    description: this.description,
                    longitude: this.longitude,
                    latitude: this.latitude,
                    hospitalStaticConfig: {
                        coverImage: this.coverImage
                    }


                }).then(resp => {
                    if (resp.status == 200)
                        this.show = false;
                });

            },
            selectImage(image) {
                this.currentImage = image;
                this.previewImage = URL.createObjectURL(this.currentImage);
                this.progress = 0;
                this.message = "";
                this.upload();
            },
            resetData() {
                this.currentImage = undefined;
                this.previewImage = undefined;
                this.progress = 0;
                this.name = '';
                this.description = '';
                this.longitude = '';
                this.latitude = '';
                this.file = null;
            },
        },
        watch: {
            show(val1) {

                if (val1 == false) {
                    this.$emit('closeDia', this.show);
                    this.resetData();

                }


            }
        },
        props: ['show'],
    };
</script>
