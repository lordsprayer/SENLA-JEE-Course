-- Задание: 1
-- Найдите номер модели, скорость и размер жесткого диска для всех ПК
-- стоимостью менее 500 дол. Вывести: model, speed и hd
select model, speed, hd from pc where price < 500;
-- Задание: 2
-- Найдите производителей принтеров. Вывести: maker
select maker from product where type = "Printer" group by maker;
-- Задание: 3
-- Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов,
-- цена которых превышает 1000 дол.
select model, ram, screen from laptop where price > 1000;
-- Задание: 4
-- Найдите все записи таблицы Printer для цветных принтеров.
select * from printer where color = "y";
-- Задание: 5
-- Найдите номер модели, скорость и размер жесткого диска ПК, имеющих
-- 12x или 24x CD и цену менее 600 дол.
select model, speed, hd from pc where (cd = "12x" or cd = "24x") and price < 600;
-- Задание: 6
-- Укажите производителя и скорость для тех ПК-блокнотов, которые имеют
-- жесткий диск объемом не менее 100 Гбайт.
select product.maker, laptop.speed from product join laptop on product.model = laptop.model 
where laptop.hd >= 100;
-- Задание: 7
-- Найдите номера моделей и цены всех продуктов (любого типа),
-- выпущенных производителем B (латинская буква).
select product.model, price from pc join product on product.model = pc.model 
where product.maker = "B" 
union (select product.model, price from laptop join product on product.model = laptop.model 
where product.maker = "B") 
union (select product.model, price from printer join product on product.model = printer.model 
where product.maker = "B");
-- Задание: 8
-- Найдите производителя, выпускающего ПК, но не ПК-блокноты.
select maker from product where maker in
(select maker from product where type = "PC" group by maker)
and maker not in
(select maker from product where type = "Laptop" group by maker);
-- Задание: 9
-- Найдите производителей ПК с процессором не менее 450 Мгц. Вывести:
-- Maker
select maker from product join pc on product.model = pc.model where speed >= 450 group by maker;
-- Задание: 10
-- Найдите принтеры, имеющие самую высокую цену. Вывести: model, price
select model, price from printer  where price = (select max(price) from printer);
-- Задание: 11
-- Найдите среднюю скорость ПК.
select avg(speed) from pc;
-- Задание: 12
-- Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000
-- дол.
select avg(speed) from laptop where price > 1000;
-- Задание: 13
-- Найдите среднюю скорость ПК, выпущенных производителем A.
select avg(speed) from pc join product on pc.model = product.model where maker = "A";
-- Задание: 14
-- Для каждого значения скорости найдите среднюю стоимость ПК с такой
-- же скоростью процессора. Вывести: скорость, средняя цена
select speed, avg(price) from pc group by speed;
-- Задание: 15
-- Найдите размеры жестких дисков, совпадающих у двух и более PC.
-- Вывести: HD
select hd from pc  group by hd having count(hd) >= 2;
-- Задание: 16
-- Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В
-- результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i),
-- Порядок вывода: модель с большим номером, модель с меньшим номером,
-- скорость и RAM.
select distinct pc1.model, pc2.model, pc1.speed, pc1.ram
from pc as pc1, pc as pc2
where pc1.model>pc2.model and pc1.speed=pc2.speed and pc1.ram=pc2.ram;
-- Задание: 17
-- Найдите модели ПК-блокнотов, скорость которых меньше скорости
-- любого из ПК.
-- Вывести: type, model, speed
select type, laptop.model, speed from laptop join product on laptop.model = product.model 
where speed < (select min(speed) from pc);
-- Задание: 18
-- Найдите производителей самых дешевых цветных принтеров. Вывести:
-- maker, price
select maker, price from product join printer on product.model = printer.model 
where price = (select min(price) from printer where color = "y");
-- Задание: 19
-- Для каждого производителя найдите средний размер экрана выпускаемых
-- им ПК-блокнотов. Вывести: maker, средний размер экрана.
select maker, avg(screen) from product join laptop on product.model = laptop.model group by maker;
-- Задание: 20
-- Найдите производителей, выпускающих по меньшей мере три различных
-- модели ПК. Вывести: Maker, число моделей
select maker, count(product.model) from product join pc on product.model = pc.model 
group by maker having count(product.model) > 2;
-- Задание: 21
-- Найдите максимальную цену ПК, выпускаемых каждым производителем.
-- Вывести: maker, максимальная цена.
select maker, max(price) from product join pc on product.model = pc.model group by maker;
-- Задание: 22
-- Для каждого значения скорости ПК, превышающего 600 МГц, определите
-- среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
select speed, avg(price) from pc where speed > 600 group by speed;
-- Задание: 23
-- Найдите производителей, которые производили бы как ПК со скоростью
-- не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц.
-- Вывести: Maker
select maker from product where maker in
(select maker from product join pc on product.model = pc.model where speed > 750 group by maker)
and maker in
(select maker from product join laptop on product.model = laptop.model where speed > 750 
group by maker) group by maker;
-- Задание: 24
-- Перечислите номера моделей любых типов, имеющих самую высокую
-- цену по всей имеющейся в базе данных продукции.
select product.model from pc join product on product.model = pc.model 
where price = (select max(price) from pc)
union (select product.model from laptop join product on product.model = laptop.model 
where price = (select max(price) from laptop)) 
union (select product.model from printer join product on product.model = printer.model 
where price = (select max(price) from printer));
-- Задание: 25
-- Найдите производителей принтеров, которые производят ПК с
-- наименьшим объемом RAM и с самым быстрым процессором среди всех
-- ПК, имеющих наименьший объем RAM. Вывести: Maker
select maker from product where maker in
(select maker from product where type = "Printer" group by maker)
and maker in
(select maker from product join pc on product.model = pc.model 
where type = "PC" and ram = (select min(ram) from pc)
and speed = (select max(speed) from pc where ram = (select min(ram) from pc)) 
group by maker) group by maker;






