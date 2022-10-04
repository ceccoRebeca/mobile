class Colecionaveis(
    codigo: String,
    quantidade: Int,
    nome: String,
    precoCompra: Float,
    precoVenda: Float,


    var tipo: tipoColecionavel,
    var materialFabricacao: materialFabricacao,
    var tamanho: Float,
    var relevancia: Relevancia) : Produto(codigo, quantidade, nome, precoCompra, precoVenda) {

    override fun toString(): String {
        return "C-" + super.toString()
    }
}