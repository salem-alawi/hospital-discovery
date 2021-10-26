<template>

  <v-card style="padding: 40px">
    <div class="d-flex justify-center">
      <h3>
        تعديل مستشفى
      </h3>
    </div>
    <hr/>
    <div class="CreateNewHospital">
      <v-row v-if="hospital" style="padding: 10px">
        <v-col
            class="d-flex child-flex"
            cols="12">
          <v-card style="border-radius: 17px;padding: 40px">
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
            <fieldset style="width:100%;padding: 40px;border-radius: 14px;">
              <legend>
                <pre style="font-weight: bold">   البيانات الاساسية   </pre>
              </legend>
              <v-row>

                <v-col cols="lg-4 md-5 sm-11">
                  <v-row>
                    <v-col>
                      <v-text-field
                          style="width: 300px;margin: auto;padding-top: 30px"
                          v-model="hospital.name"
                          label="اسم المستشفى"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>

                      <v-select
                          :value="hospital.type"
                          item-text="name"
                          item-value="value"
                          :items="type"
                          label="نوعه الخدمه"
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col>
                  <v-textarea
                      outlined
                      v-model="hospital.description"
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
                          v-model="hospital.longitude"
                          label="خط الطول"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-text-field
                          v-model="hospital.latitude"
                          label="خط العرض"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>

                </v-col>

                <!--                                    <v-col>-->
                <!--                                    <div id="map">-->

                <!--                                    </div>-->
                <!--                                    </v-col>-->

              </v-row>
              <!--                                <v-col>-->


            </fieldset>

            <fieldset style="width:100%;padding: 40px;border-radius: 14px;">
              <legend>
                <pre style="font-weight: bold;margin-top: 20px">  بيانات الاتصال  </pre>
              </legend>

              <v-row>
                <v-col>
                  <v-text-field
                      v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].name"
                      label="اسم جهات الاتصال"
                      required
                  ></v-text-field>
                </v-col>
                <v-col>
                  <v-text-field
                      v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].phoneNumber"
                      label="رقم الهاتف"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                      v-model="hospital.hospitalStaticConfig.hospitalContactInfos[0].email"
                      label="البريد الالكتروني"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>
            </fieldset>
            <v-row>

              <v-col class="d-flex justify-center" style="padding-bottom: 30px">
                <v-btn
                    color="primary"
                    @click="saveEditHospitalToApi()"
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
</template>

<script>

import UploadFilesService from "@/service/UploadFilesService";
import HospitalService from "@/service/HospitalService";
import SectionService from "@/service/SectionService";
import DoctorService from "@/service/DoctorService";

export default {
  components: {},
  props: ['id'],
  data: () => {
    return {
      hospital: undefined,
      file: null,
      currentImage: undefined,
      previewImage: undefined,
      progress: 0,
      loaded: false,
      type: [{
        name: 'حكومي',
        value: 0
      }, {
        name: 'خاص',
        value: 1
      }],
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
            this.hospital.hospitalStaticConfig.coverImage = this.coverImage;

          });
    },
    saveEditHospitalToApi() {
      HospitalService.updateHospital(this.hospital.id, {
            name: this.hospital.name,
            description: this.hospital.description,
            longitude: this.hospital.longitude,
            latitude: this.hospital.latitude,
            type: this.hospital.type,
            hospitalStaticConfig: this.hospital.hospitalStaticConfig
          }
      ).then(resp => {
        if (resp.status == 200)
          this.$router.back();

      });

    },
    selectImage(image) {


      this.currentImage = image;
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.progress = 0;
      this.message = "";
      this.upload();
    }
    ,
    resetData() {
      this.currentImage = undefined;
      this.previewImage = undefined;
      this.progress = 0;
      this.name = '';
      this.description = '';
      this.longitude = '';
      this.latitude = '';
      this.file = null;
    }
    ,
    initialize() {
      HospitalService.findOneHospital(this.id).then(resp => {

        if (resp.status === 200) {
          this.hospital = resp.data;
          this.loaded = true;
          this.previewImage = resp.data.hospitalStaticConfig.coverImage;
        }

      });

      SectionService.findAllSectionByHospitalId(this.id).then(resp => {
        console.log(resp)
        if (resp.status == 200)
          this.sections = resp.data;
      })

      DoctorService.findAllDoctorsByHospitalId(this.id).then(resp => {
        if (resp.status == 200) {

          this.doctors = resp.data;

        }
      });

    }
    ,
    removeThisHospital() {
      HospitalService.removeHospital(this.id).then((resp) => {
        if (resp.status == 200)
          alert('remove success')
        else
          alert('error remove');
      })

    }


  },
  mounted() {
    this.initialize();
  }
};
</script>
