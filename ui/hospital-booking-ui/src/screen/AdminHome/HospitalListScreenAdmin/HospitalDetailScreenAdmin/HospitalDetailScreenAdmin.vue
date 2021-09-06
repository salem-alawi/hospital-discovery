<template>
  <div class="HospitalDetailScreenAdmin">

    <v-container>


      <v-expansion-panels
          v-model="panel"
          multiple
          v-if="loaded"
      >
        <v-expansion-panel>
          <v-expansion-panel-header>معلومات المشتفى</v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-card>

              <v-card-text>

                <div class="d-flex justify-end">
                  <v-btn

                      @click="showEditHospitalPage()"
                      x-small
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

                  @click="showEditHospitalPage()"
                  x-small
                  color="primary"
                  dark
              >
                اضافة قسم
              </v-btn>
            </div>

            <SectionCard v-for="section in sections" :key="section.id"
                         :section="section.section"   :hospital-section-id="section.id"></SectionCard>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel>
          <v-expansion-panel-header>الاطباء</v-expansion-panel-header>
          <v-expansion-panel-content>
            <ul>
              <li v-for="doctor in doctors" :key="doctor.id">{{ doctor.name }}</li>
            </ul>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel>
          <v-expansion-panel-header>معرض الصور</v-expansion-panel-header>
          <v-expansion-panel-content>
            Some content
          </v-expansion-panel-content>
        </v-expansion-panel>

      </v-expansion-panels>
    </v-container>

<!--    <v-btn @click="removeThisHospital">-->

<!--      remove this hospital-->

<!--    </v-btn>-->

<!--    {{ id }}-->


  </div>


</template>
<style>
#map {
  width: 100%;
  height: 480px;
}
</style>
<script>

import HospitalService from "../../../../service/HospitalService";
import SectionService from "../../../../service/SectionService";
import SectionCard from "../../../../components/SectionCard/SectionCard";
import DoctorService from "../../../../service/DoctorService";
import UploadFilesService from "@/service/UploadFilesService";


export default {
  components: {SectionCard},
  props: ['id'],
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
      doctors: []
    }
  },
  methods: {
    showEditHospitalPage() {

      this.$router.push('/admin/hospitals/' + this.hospital.id + '/edit');

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
    initialize() {
      HospitalService.findOneHospital(this.id).then(resp => {

        if (resp.status == 200) {
          this.hospital = resp.data;
          this.loaded = true;
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

    },
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
