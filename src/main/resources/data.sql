-- File này dùng để insert data master cho ứng dụng khi chạy chương trình.Mỗi khi chạy lại, ứng dụng sẽ kiểm tra trong DATABASE có bản ghi như ở file này chưa,nếu có rồi thì skip, chưa có thì nó sẽ insert lại.
-- Lưu ý file này chỉ có thể chứa các câu lệnh DML chứ k chứa DDL.
INSERT INTO `osp_role_type` (`role_type_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `description`, `role_type_code`, `role_type_name`)
 VALUES 
('1', 'System Generated', now(), 'System Generated', now(), 'OSP Corporation Company', 'OSP', 'OSP Corp');

INSERT INTO `osp_role` (`role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `description`, `role_code`, `role_name`, `status`,`role_type_id`,`role_level`) 
VALUES
('1', 'System Generated', now(), 'System Generated', now(), '', 'ADM', 'ADMIN', '0','1','1'),
('2', 'System Generated', now(), 'System Generated', now(), '', 'MOD', 'MODERATOR', '0','1','2'),
('3', 'System Generated', now(), 'System Generated', now(), '', 'CON', 'CONTRACT OWNER', '0','1','3'),
('4', 'System Generated', now(), 'System Generated', now(), '', 'SAL', 'SALE', '0','1','4');

--INSERT INTO `osp_call_resources` 
--(`call_resources_id`,`created_by`, `created_date`, `modified_by`, `modified_date`, `branch`,  `status`, `hotline`, `token3c`, `user_id`)
-- VALUES 
--(1, 'Admin', now(), 'Admin', now(), '7967_1', 1,'0931775776',null, null),
--(2, 'Admin', now(), 'Admin', now(), '7967_2', 1,'0931775776', null, null),
--(3, 'Admin', now(), 'Admin', now(), '7967_3', 1,'0931775776', null, null);

