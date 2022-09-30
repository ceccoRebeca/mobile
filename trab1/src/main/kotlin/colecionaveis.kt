class Colecionaveis(nome: String, precoCompra: Float, precoVenda: Float, codigo: String, quantidade: Int,
                    var tipo: String, var materialFabricacao: String, var tamanho: Float, var relevancia: Relevancia) :
    Produto(nome, precoCompra, precoVenda, codigo, quantidade) {

    override fun toString(): String {
        return "C-" + super.toString()
    }
}