class Roupa(nome: String, precoCompra: Float, precoVenda: Float, codigo: String, quantidade: Int,
            var tipo: tipoRoupa, var tamanho: tamanhoRoupa, var corPrimaria: String, var corSecundaria: String? ) :
    Produto(nome, precoCompra, precoVenda, codigo, quantidade) {

    override fun toString(): String {
        return "R-"+super.toString()
    }
}
