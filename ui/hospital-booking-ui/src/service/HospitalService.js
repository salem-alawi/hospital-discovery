import http from "../http-common";

class HospitalService {

    createNewSection(createNewHospitalDto) {
        return http.post('admin/hospitals', createNewHospitalDto);
    }

    findOneHospital(id){
        return http.get('/admin/hospitals/'+id);
    }

    removeHospital(id) {
        return http.delete('admin/hospitals/' + id);
    }

    updateHospital(id, updateHospitalDto) {
        return http.put('admin/hospitals/' + id, updateHospitalDto);
    }

    findAllPublicHospital(){

        return http.get('public/hospitals');
    }

    addNewImage(hospitalId,listOfImages){
        return http.post('admin/hospitals/'+hospitalId+'/images',listOfImages);
    }

    findOneHospitalPublic(id){
        return http.get('/public/hospitals/'+id)
    }
}

export default new HospitalService();
