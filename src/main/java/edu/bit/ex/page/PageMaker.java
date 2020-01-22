package edu.bit.ex.page;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private Criteria cri; // page, perPageNum �� ������ ���� �� ���� �װ� ����������
	
    private int totalCount; // ��ü �Խñ� ��

    //[11][12][13].......[20] : ���� �������� 13�� �� startPage�� 11, endPage�� 20
    private int startPage; // �Խñ� ��ȣ�� ���� (��������)�������� ���� ��ȣ
    private int endPage; // �Խñ� ��ȣ�� ���� (��������)�������� ������ ��ȣ
    
    // ��) startPage:1 endPage:10 ====> 1 2 3 4 5 6 7 8 9 10
 	// ��) startPage:1 endPage:5 ====> 1 2 3 4 5
 	// ��) startPage:11 endPage:20 ====> 11 12 13 14 15 16 17 18 19 20

    
    private boolean prev; // << ��ư ��¿��� :  ���� ��ư�� ���� �� �ִ� ���/���� ��� �з��� ����
    private boolean next; // >> ��ư ��¿���
 
    private int displayPageNum = 10; // ȭ�� �ϴܿ� �������� �������� ����
    private int tempEndPage;
 
    ///////////////////////////////////////////////////////////////////////////////////////////////
  
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount; // ��ü �Խñ� ��...
 
        calcData(); // ��ü �ʵ� ������ ���� : ��ü �Խñ� ���� setter�� ȣ��� �� ��ü ���õǵ��� ��
    }
 
    private void calcData() { // setTotalCount�� ���缭 ! ��ü �ʵ� ���� ������ ����ϴ� �޼���
 
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        	// ceil : �Ҽ��� ���ϸ� �ø�
         	// ���������� ������ ���� ���������� ���� ����
     		// ex) ���� ������(pageInfo.getPage()) = 3
     		// �ϴ� ����¡ �ٿ� ������ ����¡�� ���� = displayPageNum = 10��
     		// ���� : ceil(3 / 10.0) * 10 => 1 * 10 = 10����
     		// 1. (3/10.0) = 0.3
     		// 2. ceil(0.3) = 1
     		// 3. 1* 10 = 10
     		// 1 2 3 4 5 6 7 8 9 10

     		// ex2) ���� ������ (pageInfo.getPage()) = 13
     		// �ϴ� ����¡ �ٿ� ������ ����¡�� ���� = diplayPageNum = 10��
     		// ���� : ceil(13/10.0) * 10 => 2 * 10 = 20����
     		// 11 12 13 14 15 16 17 18 19 20
        
        
        
        
        
        startPage = (endPage - displayPageNum) + 1;
 
        
        	// �������� ������ ���� ���� �������� ���� ����
     		// ex) ���������� pageInfo.getPage()) = 3
     		// �������� endPage = 10
     		// �ϴ� ����¡ �ٿ� ������ ����¡�� ���� = displayPageNum = 10
     		// ���������� = (10 - 10) + 1 = 1

     		// ���������� 3���κ��� endPage�� ��������(10)
     		// �ش� �������� ������������ ���� ����

     		// ex) ���������� pageInfo.getPage()) = 13
     		// �������� endPage = 20
     		// �ϴ� ����¡ �ٿ� ������ ����¡�� ���� = displayPageNum = 10
     		// ���������� = (20 - 10) + 1 = 11
        
        
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        this.tempEndPage = tempEndPage;
 
        	// ��ü �� �������� :
     		// totalCount = select count(*) from tbl_count�� �����
     		// pageInfo.getPerPageNum = �� ȭ�鿡 ����� ���� ����
     		// ex) totalCount = 512
     		// pageInfo.getPerPageNum = 10
     		// tempEndPage = ceil(512 / 10.0) = 52
     		// 51.2�ǰ���� �ø� => 51������ ������ �� ȭ��� 10���� �Խù��� ���
     		// ������ 52���������� 0.2�� �ش��ϴ� 2���� �Խù��� ���
        
        
        
        
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        prev = startPage == 1 ? false : true; // 1�������� ���� ���� �� ���� false
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
 
    }
 
    // getter setter
 
    public Criteria getCri() {
        return cri;
    }
 
    public int getTempEndPage() {
        return tempEndPage;
    }
 
    public void setCri(Criteria cri) {
        this.cri = cri;
    }
 
    public int getTotalCount() {
        return totalCount;
    }
 
    public int getStartPage() {
        return startPage;
    }
 
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
 
    public int getEndPage() {
        return endPage;
    }
 
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
 
    public boolean isPrev() {
        return prev;
    }
 
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
 
    public boolean isNext() {
        return next;
    }
 
    public void setNext(boolean next) {
        this.next = next;
    }
 
    public int getDisplayPageNum() {
        return displayPageNum;
    }
 
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
    
    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
        		.queryParam("page", page) 
        		.queryParam("perPageNum", cri.getPerPageNum()).build(); 
        // page=3&perPageNum=10 << �� ����������... url�ڿ� ��������
        return uriComponents.toUriString(); // ?page=3&perPageNum=10�� ���� ����
    }
}
