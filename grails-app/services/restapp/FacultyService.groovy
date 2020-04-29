package restapp

import grails.gorm.transactions.Transactional

@Transactional
class FacultyService {

    def get(id) {
        Faculty.get(id)
    }

    def list() {
        Faculty.list()
    }

    def save(faculty) {
        faculty.save()
    }

    def delete(id) {
        Faculty.get(id).delete()
    }

}
