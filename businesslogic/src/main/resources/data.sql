INSERT INTO user_status (id, status)
VALUES
(1, '등록요청'),
(2, '등록완료'),
(9, '삭제요청')
ON CONFLICT (id)
DO UPDATE
SET status = excluded.status;

INSERT INTO hobbies (hobby)
VALUES
('등산'),
('게임'),
('야구'),
('클라이밍'),
('자전거')
ON CONFLICT ON CONSTRAINT hobbies_key DO NOTHING;

INSERT INTO department (id, department)
VALUES
(1000, '지원본부'),
(1100, '경영지원팀'),
(1200, '영업지원팀'),
(2000, '영업본부'),
(2100, '영업1팀'),
(2200, '영업2팀'),
(3000, '인프라본부'),
(3100, '인프라1팀'),
(4000, '서비스본부'),
(4100, '서비스1팀'),
(5000, '클라우드본부'),
(5100, '클라우드A팀'),
(5200, '클라우드B팀'),
(9999, '대기')
ON CONFLICT (id)
DO UPDATE
SET department = excluded.department;

