utils.find('techcart$techcart', [:]).size()

def res = ''

utils.find('brand$5677002', ['removed':false]).size().each{
  res += it + '<br>'
}
res