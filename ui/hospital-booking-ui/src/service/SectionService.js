import http from "../http-common";

class SectionService {

    createNewSection(name, imageCover) {

        return http.post('admin/sections', {
            name: name,
            coverImage: imageCover
        });
    }

    findAllSectionByHospitalId(id){
    return http.get('admin/hospitals/'+id+'/sections');

    }

    findAllRemainingSectionByHospitalId(id){

return http.get('admin/sections-except/'+id)

    }


    addSectionToHospital(hospitalId,sectionId){


return http.post('admin/hospitals/'+hospitalId+'/sections/'+sectionId);

    }
}

export default new SectionService();
