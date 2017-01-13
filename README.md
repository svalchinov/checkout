# Checkout


### Possible entity relationship model
- [Link](rdbms/design/entity-relationship-model.png)


### Future improvements
- Convert BigDecimal to amount in pence
- Apply multiple promotions to same item depending on quantity (E.g. 2 for 1 but also 3 for Less when buying 5 items, it will currently apply 2 for 1 twice)
- Error handling


### Notes
- To build run ```./gradlew clean build```
- Please see ```CheckoutIntegrationTest.java``` for quick inspection