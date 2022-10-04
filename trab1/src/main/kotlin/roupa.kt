class Roupa(
    codigo: String,
    quantidade: Int,
    nome: String,
    precoCompra: Float,
    precoVenda: Float,


    var tipo: tipoRoupa,
    var tamanho: tamanhoRoupa,
    var corPrimaria: String,
    var corSecundaria: String? ) : Produto(codigo, quantidade,nome, precoCompra, precoVenda) {

    override fun toString(): String {
        return "R-"+super.toString()
    }
}
