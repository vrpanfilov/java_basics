CREATE TABLE `courses` (
                           `id` int unsigned NOT NULL AUTO_INCREMENT,
                           `name` varchar(500) DEFAULT NULL,
                           `duration` int unsigned DEFAULT NULL COMMENT '   ',
                           `type` enum('DESIGN','PROGRAMMING','MARKETING','MANAGEMENT','BUSINESS') NOT NULL COMMENT ' :  /  /  / ',
                           `description` varchar(500) DEFAULT NULL,
                           `teacher_id` int unsigned NOT NULL COMMENT '  ,   ',
                           `students_count` int unsigned DEFAULT NULL COMMENT '   ',
                           `price` int unsigned DEFAULT NULL COMMENT '   ',
                           `price_per_hour` float DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `teacher_idx` (`teacher_id`),
                           CONSTRAINT `FK468oyt88pgk2a0cxrvxygadqg` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`)
);

CREATE TABLE `purchaselist` (
                                `student_name` varchar(500) DEFAULT NULL,
                                `course_name` varchar(500) DEFAULT NULL,
                                `price` int DEFAULT NULL,
                                `subscription_date` datetime DEFAULT NULL
);

CREATE TABLE `students` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' ',
                            `age` int unsigned DEFAULT NULL COMMENT ' ',
                            `registration_date` datetime NOT NULL COMMENT ' ',
                            PRIMARY KEY (`id`)
)

CREATE TABLE `subscriptions` (
                                 `student_id` int unsigned NOT NULL,
                                 `course_id` int unsigned NOT NULL,
                                 `subscription_date` datetime NOT NULL,
                                 UNIQUE KEY `unq` (`student_id`,`course_id`),
                                 KEY `course_idx` (`course_id`),
                                 CONSTRAINT `course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
                                 CONSTRAINT `student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
)

CREATE TABLE `teachers` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '  \n',
                            `salary` int unsigned DEFAULT NULL COMMENT '  ',
                            `age` int unsigned DEFAULT NULL COMMENT ' ',
                            PRIMARY KEY (`id`)
)
