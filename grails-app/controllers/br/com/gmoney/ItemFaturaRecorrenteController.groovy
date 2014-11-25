package br.com.gmoney


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ItemFaturaRecorrenteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ItemFaturaRecorrente.list(params), model: [itemFaturaRecorrenteInstanceCount: ItemFaturaRecorrente.count()]
    }

    def show(ItemFaturaRecorrente itemFaturaRecorrenteInstance) {
        respond itemFaturaRecorrenteInstance
    }

    def create() {
        respond new ItemFaturaRecorrente(params)
    }

    @Transactional
    def save(ItemFaturaRecorrente itemFaturaRecorrenteInstance) {
        if (itemFaturaRecorrenteInstance == null) {
            notFound()
            return
        }

        if (itemFaturaRecorrenteInstance.hasErrors()) {
            respond itemFaturaRecorrenteInstance.errors, view: 'create'
            return
        }

        itemFaturaRecorrenteInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'itemFaturaRecorrenteInstance.label', default: 'ItemFaturaRecorrente'), itemFaturaRecorrenteInstance.id])
                redirect itemFaturaRecorrenteInstance
            }
            '*' { respond itemFaturaRecorrenteInstance, [status: CREATED] }
        }
    }

    def edit(ItemFaturaRecorrente itemFaturaRecorrenteInstance) {
        respond itemFaturaRecorrenteInstance
    }

    @Transactional
    def update(ItemFaturaRecorrente itemFaturaRecorrenteInstance) {
        if (itemFaturaRecorrenteInstance == null) {
            notFound()
            return
        }

        if (itemFaturaRecorrenteInstance.hasErrors()) {
            respond itemFaturaRecorrenteInstance.errors, view: 'edit'
            return
        }

        itemFaturaRecorrenteInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ItemFaturaRecorrente.label', default: 'ItemFaturaRecorrente'), itemFaturaRecorrenteInstance.id])
                redirect itemFaturaRecorrenteInstance
            }
            '*' { respond itemFaturaRecorrenteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ItemFaturaRecorrente itemFaturaRecorrenteInstance) {

        if (itemFaturaRecorrenteInstance == null) {
            notFound()
            return
        }

        itemFaturaRecorrenteInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ItemFaturaRecorrente.label', default: 'ItemFaturaRecorrente'), itemFaturaRecorrenteInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemFaturaRecorrenteInstance.label', default: 'ItemFaturaRecorrente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
