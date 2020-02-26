//searching obj
def catalogsArray = []
def basesArray = []
def systems = utils.find('catalogs$system1C', ['removed':false])
systems.each{
  def bases = utils.find('catalogs$base', ['catalogParent':it, 'removed':false])
  bases.each{
    basesArray += ['title':it.title, 'id':it.UUID]
  }
  catalogsArray += ['title':it.title, 'id':it.UUID, 'bases':basesArray]
}
catalogsArray