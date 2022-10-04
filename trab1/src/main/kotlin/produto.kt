open class Produto (
    var codigo: String,
    var quantidade: Int,
    var nome: String,
    var precoCompra: Float,
    var precoVenda: Float){

    override fun toString(): String {
        return codigo
    }

}