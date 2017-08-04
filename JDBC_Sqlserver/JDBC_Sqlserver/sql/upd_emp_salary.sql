IF OBJECT_ID ( 'upd_emp_salary', 'P' ) IS NOT NULL 
    DROP PROCEDURE upd_emp_salary;
GO

CREATE PROCEDURE upd_emp_salary (
	@salary		decimal(10,2),
	@empno		decimal(4)
) AS
BEGIN
	UPDATE employee SET salary = @salary WHERE empno = @empno
END
GO
