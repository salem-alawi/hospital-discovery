<template>
    <div class="SectionListScreenAdmin">


        <v-row>
            <v-col v-for="sec in allSections" :key="sec.id">
                <SectionCard :section=sec @click="onclickSection(sec.id)"/>
            </v-col>
        </v-row>

        <v-dialog v-model="createSectionDialog" max-width="800">
            <v-sheet
                    class="pa-12"
                    color="grey lighten-3"
            >
                <v-content>

                    <v-row no-gutters justify="center" align="center">
                        <v-col>
                            <v-file-input
                                    show-size
                                    label="اختار الصور"
                                    accept="image/*"
                                    @change="selectImage"
                            ></v-file-input>
                        </v-col>
                        <v-col>

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
                                    <img class="preview my-3" :src="previewImage" alt=""/>
                                </div>
                            </div>
                        </v-col>
                        <v-col>
                            <v-btn depressed>
                                Normal
                            </v-btn>
                            <v-btn
                                    @click="createNewSection()"
                                    depressed
                                    color="primary"
                            >
                                حفظ
                            </v-btn>

                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-text-field
                                    v-model="name"
                                    label="اسم القسم"
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                </v-content>

            </v-sheet>


            <!---->
            <!--            <v-alert v-if="message" border="left" color="blue-grey" dark>-->
            <!--                {{ message }}-->
            <!--            </v-alert>-->

            <!--            <v-card v-if="imageInfos.length > 0" class="mx-auto">-->
            <!--                <v-list>-->
            <!--                    <v-subheader>List of Images</v-subheader>-->
            <!--                    <v-list-item-group color="primary">-->
            <!--                        <v-list-item v-for="(image, index) in imageInfos" :key="index">-->
            <!--                            <a :href="image.url">{{ image.name }}</a>-->
            <!--                        </v-list-item>-->
            <!--                    </v-list-item-group>-->
            <!--                </v-list>-->
            <!--            </v-card>-->
        </v-dialog>
        <!--        <v-dialog v-model="createSectionDialog" max-width="1100">-->
        <!--            <v-card>-->
        <!--                <v-card-text>-->
        <!--                    <v-container>-->
        <!--                        <v-row>-->
        <!--                            <v-col-->
        <!--                                    class="d-flex child-flex"-->
        <!--                                    cols="12">-->
        <!--                                <v-card>-->
        <!--                                    <iframe-->
        <!--                                            v-bind:src="locationUrl"-->
        <!--                                            width="100%" height="450" frameborder="0"-->
        <!--                                            style="border: 0;"-->
        <!--                                            class="grey lighten-2">-->
        <!--                                    </iframe>-->
        <!--                                </v-card>-->
        <!--                            </v-col>-->
        <!--                        </v-row>-->
        <!--                    </v-container>-->
        <!--                </v-card-text>-->
        <!--                <v-card-actions>-->
        <!--                    <v-spacer></v-spacer>-->
        <!--                    &lt;!&ndash;                        <v-btn color="blue darken-1" text @click="closeMap">اغلاق</v-btn>&ndash;&gt;-->
        <!--                </v-card-actions>-->
        <!--            </v-card>-->
        <!--        </v-dialog>-->
        <v-btn
                @click="createSectionDialog=true"
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

    import {mapActions, mapGetters} from 'vuex';
    import axios from 'axios'
    import SectionCard from "../../../components/SectionCard/SectionCard";
    import UploadService from "../../../service/UploadFilesService"
    import SectionService from "../../../service/SectionService";


    export default {
        components: {SectionCard},
        props: [],
        data: () => {
            return {

                name: '',
                coverImage: '',
                locationDialog: false,
                currentImage: undefined,
                previewImage: undefined,
                progress: 0,
                message: "",

                createSectionDialog: false
            };
        },
        mounted() {
            this.initialize();
        },
        methods: {
            ...mapActions(['loadAllSections']),
            selectImage(image) {
                this.currentImage = image;
                this.previewImage = URL.createObjectURL(this.currentImage);
                this.progress = 0;
                this.message = "";
                this.upload();
            },
            createNewSection() {
                SectionService.createNewSection(this.name, this.coverImage).then(resp => {
                    console.log(resp);
                    if (resp.status == 200) {
                        this.initialize();

                    }
                })
            },
            upload() {
                if (!this.currentImage) {
                    this.message = "Please select an Image!";
                    return;
                }
                this.progress = 0;
                UploadService.upload(this.currentImage, (event) => {
                    this.progress = Math.round((100 * event.loaded) / event.total);
                })
                    .then((response) => {
                        this.coverImage = response.data.name;

                    });
            },
            onclickSection(id) {
                console.log('click !!!')
                console.log(id);
            },
            initialize() {
                axios.get('http://127.0.0.1:8080/admin/sections?size=100').then(resp => {
                    this.loadAllSections(resp.data.content);
                });
            }
        },
        computed: {
            ...mapGetters(['allSections']),

        }
    };
</script>
<style scoped>
    .preview {
        max-width: 200px;
    }
</style>
