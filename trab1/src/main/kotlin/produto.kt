open class Produto (var nome: String, var precoCompra: Float, var precoVenda: Float, var codigo: String, var quantidade: Int){

    override fun toString(): String {
        return codigo
    }

}