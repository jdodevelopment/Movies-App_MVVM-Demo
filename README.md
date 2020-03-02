# Arquitectura MVVM

## Capas de la aplicación

### Modelo
`Movie.java (capa negocio), MoviesResponse.java, MovieDatabase.java (capa persistencia), MovieDao.java, MovieService.java (capa de red), MovieRepository.java (capa repositorio)`

### Vista
`MainActivity.java, MovieDetailActivity.java, PopularFragment.java, TopRatedFragment.java, UpcomingFragment.java, MovieAdapter.java`

### VistaModelo
`PopularFragmentViewModel.java, TopRatedFragmentViewModel.java, UpcomingFragmentViewModel.java`

## Principio de responsabilidad única

Es un principio de programación que establece que cada módulo o clase debe tener responsabilidad sobre una sola parte de la funcionalidad proporcionada por el software, y esa responsabilidad debe estar completamente encapsulada. 
##### Segun el tío Bob: 
> "Una clase debería tener una sola razón para cambiar"

Compliendo este principio, tendras un codigo mas facil de testear, mantenible, escalable, ya que si debes cambiar una funcionalidad, solo lo debes hacer desde un lugar.

## Codigo limpio

Las caracteristicas que debe cumplir un codigo limpio considero que son las siguientes: 

Que sea facil de leer (principalmente por otros desarrolladores), expresivo, mantenible, escalable, reutilizable, facil de refatorizar, no contener codigo duplicado.
