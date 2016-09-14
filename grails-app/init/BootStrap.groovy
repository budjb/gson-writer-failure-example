import com.budjb.MyDomain

class BootStrap {

    def init = { servletContext ->
        new MyDomain(name: 'test').save()
    }
    def destroy = {
    }
}
