#  delimiter //
-- CREATE procedure plus1inout (IN arg int, OUT res int)
-- BEGIN ATOMIC
-- 	set res = arg + 1;
-- END //
-- delimiter ;
--  加载存储过程会出错，暂时找不到原因