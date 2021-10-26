<template>
  <div class="HospitalDetailScreenAdmin">

    <v-container>
      <v-expansion-panels
          inset
          focusable
          v-if="loaded"
      >
        <v-expansion-panel>
          <v-expansion-panel-header>معلومات المشتفى</v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-card v-if="hospital">
              <v-card-text>
                <div class="d-flex justify-end">
                  <v-btn

                      @click="showEditHospitalPage()"
                      small
                      color="secondary"
                      dark
                  >
                    تعديل
                  </v-btn>
                </div>


                <fieldset style="width:100%;padding: 40px;border-radius: 14px;">
                  <legend>
                    <pre style="font-weight: bold">   البيانات الاساسية   </pre>
                  </legend>
                  <v-row>

                    <v-col cols="lg-4 md-5 sm-11">
                      <v-row>
                        <v-col>
                          <v-text-field
                              disabled
                              style="width: 300px;margin: auto;padding-top: 30px"
                              :value="hospital.name"
                              label="اسم المستشفى"
                              required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col>

                          <v-select
                              disabled
                              :value="hospital.type"
                              item-value="value"
                              item-text="name"
                              :items="type"
                              label="نوعه الخدمه"
                          ></v-select>
                        </v-col>
                      </v-row>

                    </v-col>
                    <v-col>
                      <v-textarea
                          outlined
                          :value="hospital.description"
                          disabled
                          label="وصف المستشفى"
                          required
                      ></v-textarea>
                    </v-col>
                  </v-row>
                </fieldset>
                <fieldset style="width:100%;padding: 40px;border-radius: 14px;margin-top: 20px">
                  <legend>
                    <pre style="font-weight: bold">  معلومات الموقع  </pre>
                  </legend>
                  <v-row>
                    <v-col>

                      <v-row>
                        <v-col>
                          <v-text-field
                              disabled
                              :value="hospital.longitude"
                              label="خط الطول"
                              required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col>
                          <v-text-field
                              :value="hospital.latitude"
                              disabled
                              label="خط العرض"
                              required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>
                </fieldset>
                <fieldset style="width:100%;padding: 40px;border-radius: 14px;">
                  <legend>
                    <pre style="font-weight: bold;margin-top: 20px">  بيانات الاتصال  </pre>
                  </legend>
                  <v-row>
                    <v-col>
                      <v-text-field
                          disabled
                          v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].name"
                          label="اسم جهات الاتصال"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col>
                      <v-text-field
                          disabled
                          v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].phoneNumber"
                          label="رقم الهاتف"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-text-field
                          disabled
                          v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].email"
                          label="البريد الالكتروني"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </fieldset>
              </v-card-text>
            </v-card>

          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel>
          <v-expansion-panel-header>الاقسام</v-expansion-panel-header>
          <v-expansion-panel-content>

            <div class="d-flex justify-end">
              <v-btn
                  style="margin-top: 20px"
                  @click="addSectionDialog=true"
                  small
                  color="primary"
                  dark
              >
                اضافة قسم
              </v-btn>
            </div>

            <div class="d-flex flex-wrap  justify-center" v-if="sections">
              <SectionCard style="width: 300px" v-for="section in sections" :key="section.id"
                           :section="section.section" :hospital-section-id="section.id"/>

            </div>
          </v-expansion-panel-content>
        </v-expansion-panel>
        <v-expansion-panel>
          <v-expansion-panel-header>الاطباء</v-expansion-panel-header>
          <v-expansion-panel-content>
            <div class="d-flex justify-end">
              <v-btn
                  style="margin-top:20px"
                  @click="showCreateNewDoctor()"
                  small
                  color="primary"
                  dark
              >
                اضافة طبيب
              </v-btn>
            </div>
            <div class="d-flex flex-wrap justify-center">
              <div style="width: 300px;" v-for="doctor in doctors" :key="doctor.id">
                <DoctorCardAdmin :edit="true" :doctor="doctor" :hospital-id="hospital.id"/>
              </div>
            </div>

          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel>
          <v-expansion-panel-header>معرض الصور</v-expansion-panel-header>
          <v-expansion-panel-content>
            <div class="d-flex justify-end">
              <v-btn
                  style="margin-top: 20px"
                  @click="addّImageDialog=true"
                  small
                  color="primary"
                  dark
              >
                اضافه صوره
              </v-btn>
            </div>
            <div class="d-flex flex-wrap justify-center" v-if="currentHospital">

              <v-img
                  style="margin: 20px"
                  v-for="image in currentHospital.hospitalStaticConfig.images" :key="image"
                  :src="image"
                  max-height="250"
                  max-width="250"
              ></v-img>

            </div>

          </v-expansion-panel-content>
        </v-expansion-panel>

      </v-expansion-panels>


      <v-row justify="center">
        <v-dialog
            v-model="createNewDoctorDialog"
            max-width="290"
        >
          <v-card>
            <v-card-title class="text-h5">
              <div style="margin: auto">اضافة طبيب</div>
            </v-card-title>
            <v-card-text>
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

                </v-col>
              </v-row>

              <v-text-field
                  style="margin-top: 20px"
                  label="اسم الطبيب"
                  required
                  v-model="createDoctorName"
                  hide-details="auto"
              ></v-text-field>

              <v-textarea
                  style="margin-top: 20px"
                  outlined
                  v-model="createDoctorAbout"
                  label="معلومات عن الطبيب"
                  required
              ></v-textarea>
              <v-select
                  item-value="id"
                  item-text="section.name"
                  v-model="selectHosptialSectionId"
                  :items="allHospitalSection"
                  label="القسم"
              ></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="green darken-1"
                  text
                  @click="createNewDoctor()"

              >
                حفظ
              </v-btn>

            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>


      <v-row justify="center">
        <v-dialog
            v-model="addّImageDialog"
            max-width="390"
        >
          <v-card>
            <v-card-title class="text-h5">
              <div style="margin: auto"> اضافه صوره</div>
            </v-card-title>
            <v-card-text>

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

                </v-col>
              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="green darken-1"
                  text
                  @click="addNewImage()"
              >
                حفظ
              </v-btn>

            </v-card-actions>
          </v-card>
        </v-dialog>


      </v-row>


      <v-row justify="center">
        <v-dialog
            v-model="createNewDoctorDialog"
            max-width="390"
        >
          <v-card>
            <v-card-title class="text-h5">
              <div style="margin: auto">اضافة طبيب</div>
            </v-card-title>
            <v-card-text>

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

                </v-col>
              </v-row>

              <v-text-field
                  style="margin-top: 20px"
                  label="اسم الطبيب"

                  v-model="createDoctorName"
                  hide-details="auto"
              ></v-text-field>

              <v-textarea
                  style="margin-top: 20px"
                  outlined
                  v-model="createDoctorAbout"
                  label="معلومات عن الطبيب"

              ></v-textarea>
              <v-select
                  item-value="id"
                  item-text="section.name"

                  v-model="selectHosptialSectionId"
                  :items="allHospitalSection"
                  label="القسم"
              ></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="green darken-1"
                  text
                  @click="createNewDoctor()"
              >
                حفظ
              </v-btn>

            </v-card-actions>
          </v-card>
        </v-dialog>


      </v-row>

      <v-row justify="center">
        <v-dialog
            v-model="addSectionDialog"
            max-width="390"
        >
          <v-card>
            <v-card-title class="text-h5">
              <div style="margin: auto">اضافه قسم جديد</div>
            </v-card-title>
            <v-card-text>
              <v-select
                  item-value="id"
                  item-text="name"
                  v-model="selectRemaindSectionId"
                  :items="allSectionReminds"
                  label="القسم"
              ></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="green darken-1"
                  text
                  @click="addSectionToHospital()"
              >
                حفظ
              </v-btn>

            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>


    </v-container>


  </div>


</template>
<script>

import HospitalService from "../../../../service/HospitalService";
import SectionService from "../../../../service/SectionService";
import SectionCard from "../../../../components/SectionCard/SectionCard";
import DoctorService from "../../../../service/DoctorService";
import UploadFilesService from "@/service/UploadFilesService";
import DoctorCardAdmin from "@/components/DoctorCard/DoctorCardAdmin";
import HospitalSectionService from "@/service/HospitalSectionService";


export default {
  components: {SectionCard, DoctorCardAdmin},
  props: ['id'],
  data: () => {

    return {
      selectRemaindSectionId: 0,
      allSectionReminds: [],
      addSectionDialog: false,
      updateDoctorDialog: false,
      selectHosptialSectionId: 0,
      allHospitalSection: [],
      createDoctorName: '',
      createDoctorAbout: '',
      createNewDoctorDialog: false,
      dia: false,
      file: null,
      currentImage: undefined,
      previewImage: undefined,
      progress: 0,
      name: '',
      description: '',
      longitude: '',
      latitude: '',
      contactName: '',
      email: '',
      phoneNumber: '',
      panel: [1, 1],
      loaded: false,
      type: [{
        name: 'حكومي',
        value: 0
      }, {
        name: 'خاص',
        value: 1
      }],
      hospital: null,
      sections: [],
      doctors: [],
      currentUpdateDoctorId: undefined,
      currentHospital: undefined,
      addّImageDialog: false
    }
  },
  methods: {
    addNewImage(){


      const newImages= this.hospital.hospitalStaticConfig.images.slice();
      newImages.push(this.coverImage);
      HospitalService.addNewImage(this.hospital.id,newImages).then(resp=>{

        if(resp.status ===200){
          location.reload();

        }

      })

    },
    showCreateNewDoctor() {
      this.createNewDoctorDialog = true;
    },
    showEditHospitalPage() {
      this.$router.push('/admin/hospitals/' + this.hospital.id + '/edit');
    },
    createNewDoctor() {
      DoctorService.createNewDoctor({
        name: this.createDoctorName,
        about: this.createDoctorAbout,
        image: this.coverImage,
        hospitalSectionId: this.selectHosptialSectionId
      }).then((resp) => {

        if (resp.status === 200) {
          this.createNewDoctorDialog = false;
          this.createDoctorName = '';
          this.createDoctorAbout = '';
          this.coverImage = '';
          this.selectHosptialSectionId = 0;
          this.initialize()
        }
      });

    },
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

    selectImage(image) {
      this.currentImage = image;
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.progress = 0;
      this.message = "";
      this.upload();
    },
    initialize() {

      HospitalService.findOneHospital(this.id).then(resp => {

        console.log('00000')
        console.log(resp.data)
        if (resp.status === 200)
          this.currentHospital = resp.data




      })

      HospitalSectionService.findAllByHospitalId(this.id).then(resp => {

        if (resp.status == 200)
          this.allHospitalSection = resp.data;
      });

      HospitalService.findOneHospital(this.id).then(resp => {

        if (resp.status == 200) {
          this.hospital = resp.data;
          this.loaded = true;
        }
      });
      SectionService.findAllRemainingSectionByHospitalId(this.id).then(resp => {

        if (resp.status === 200)
          this.allSectionReminds = resp.data;
      })

      SectionService.findAllSectionByHospitalId(this.id).then(resp => {
        if (resp.status == 200)
          this.sections = resp.data;
      })

      DoctorService.findAllDoctorsByHospitalId(this.id).then(resp => {
        if (resp.status == 200) {

          this.doctors = resp.data;

        }
      });

    },
    addSectionToHospital() {

      SectionService.addSectionToHospital(this.id, this.selectRemaindSectionId).then(resp => {

        if (resp.status === 200) {
          location.reload();
        }
      });
    }
  },
  mounted() {
    this.initialize();

  }
};
</script>
