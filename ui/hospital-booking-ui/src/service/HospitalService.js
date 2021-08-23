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

}

export default new HospitalService();
