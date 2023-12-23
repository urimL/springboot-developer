// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
        //팝업
            alert('삭제가 완료되었습니다.');

            //사용자의 웹 브라우저 화면을 현재 주소를 기반하여 옮겨줌
            location.replace('/articles');
        });
    });
}