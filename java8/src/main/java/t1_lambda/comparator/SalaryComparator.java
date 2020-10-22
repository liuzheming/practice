package t1_lambda.comparator;

import t1_lambda.Employe;

/**
 * Create by lzm on 2020/10/22
 */
public class SalaryComparator implements ICompareStrategy {

    public boolean compare(Employe e1, Employe e2) {
        return e1.getSalary() - e2.getSalary() > 0;
    }

}
