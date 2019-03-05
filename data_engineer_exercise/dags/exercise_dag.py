from builtins import range
from airflow.operators.bash_operator import BashOperator
from airflow.models import DAG, Variable
from datetime import datetime, timedelta
from croniter import croniter
import os

dag_id = 'xref'
schedule_interval = timedelta(hours=1)

args = {
    'owner': 'airflow',
    'depends_on_past': True,
    'retries': 10000,
    'retry_delay': timedelta(seconds=60),
    'start_date': datetime.strptime('2019-03-05 19:00:00', "%Y-%m-%d %H:%M:%S"),
    'execution_timeout': timedelta(seconds=7200),
}

dag = DAG(
    dag_id=dag_id, default_args=args,
    schedule_interval=schedule_interval)


query_executor = BashOperator(
    task_id='query_executor',
    bash_command='sh -c \'$EXERCISE_HOME/tasks/query_executor.sh \"{{ execution_date }}\" \'',
    dag=dag)

xref_report = BashOperator(
    task_id='xref_report',
    bash_command='sh -c \'$EXERCISE_HOME/tasks/xref_report.sh \"{{ execution_date }}\" \'',
    dag=dag)

if __name__ == "__main__":
    dag.cli()
