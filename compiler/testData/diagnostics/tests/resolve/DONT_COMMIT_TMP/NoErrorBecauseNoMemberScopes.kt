abstract class DerivedAbstract : C.Base()

class Data

public class C {

    protected val _internal: Data = Data()

    open class Base()

    companion object : DerivedAbstract()
}