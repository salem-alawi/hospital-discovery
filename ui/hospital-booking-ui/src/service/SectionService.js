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

}

export default new SectionService();
